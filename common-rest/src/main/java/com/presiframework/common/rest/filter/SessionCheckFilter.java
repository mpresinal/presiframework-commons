/*
 * Copyright 2020 Miguel Presinal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.presiframework.common.rest.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.core.Ordered;

/**
 * Este filter verifica si la sesion del usuario aun esta activa. Si el usuario
 * dura un tiempo x sin transaccionar entonces la sesion se invalida
 * automaticamente. Este filter es un control para notificarle a la app UI de
 * que la sesion del usuario ha expirado y por tanto se debe se sacar al usuario
 * del sistema.
 *
 * Este tipo de filter aplica al tipo de arquitectura donde la UI consume
 * servicios web rest que no guardan estado en memoria del usuario y ambos el UI
 * y los servicios no residen
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public abstract class SessionCheckFilter implements Filter {

    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE - 1;

    private static final String SESSION_EXPIRED_MESSAGE = "user session is expired";

    private final List<String> excludedUrlList;

    public abstract Logger getLogger();

    /**
     * Verifica el estado de la sesion del usuario.
     *
     * @return true si la sesion aun esta activa. De lo contrario false
     */
    protected abstract Boolean checkEstadoSesion(String nombreUsuario, String token) throws Exception;

    /**
     * Extrae el nombre del usuario autenticado del request.
     *
     * @param request
     * @return
     */
    protected abstract String extractNombreUsuario(HttpServletRequest request);

    /**
     * Extrae el token de autenticacion del del usuario autenticado del request
     *
     * @param request
     * @return
     */
    protected abstract String extractTokenAunteticacion(HttpServletRequest request);

    public SessionCheckFilter(List<String> excludedUrlList) {
        this.excludedUrlList = excludedUrlList;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SessionCheckFilter.init() " + filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final Logger LOGGER = getLogger();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestedUrl = httpRequest.getRequestURI();
        LOGGER.info("URL: " + requestedUrl);
        boolean skip = false;

        if (excludedUrlList != null) {
            for (String path : excludedUrlList) {
                if (requestedUrl.contains(path)) {
                    skip = true;
                    break;
                }
            }
        }

        if (skip) {
            LOGGER.debug("Excluyendo " + requestedUrl + " de la verificacion");
            chain.doFilter(request, response);

        } else {

            String nombreUsuario = extractNombreUsuario(httpRequest);
            LOGGER.debug("nombreUsuario = " + nombreUsuario);
            boolean sesionExpired = false;

            //if (nombreUsuario != null "ANONIMOS")
            try {
                //  verificar si la sesion actual del usuario ha expirado
                Boolean sesionActiva = checkEstadoSesion(nombreUsuario, extractTokenAunteticacion(httpRequest));
                LOGGER.debug("sesionActiva = " + sesionActiva);
                
                if (sesionActiva != null) {
                    sesionExpired = !sesionActiva;
                    LOGGER.debug("sesionExpired = " + sesionExpired);
                } else {
                    LOGGER.warn("No se puedo determinar el estado de la sesion del usuario");
                }                

            } catch (Exception e) {
                LOGGER.error("Error verificando el estado de la sesion del usaurio. " + e.getMessage(), e);
            }

            if (sesionExpired) {
                LOGGER.info("sesion expirada ");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, SESSION_EXPIRED_MESSAGE);

            } else {
                LOGGER.info("sesion activa ");
                chain.doFilter(request, response);
            }

        } // end main if-else

        LOGGER.debug("response.status : " + httpResponse.getStatus());
        LOGGER.debug("exit");
    }

    @Override
    public void destroy() {
        if (excludedUrlList != null) {
            excludedUrlList.clear();
        }
    }
}
