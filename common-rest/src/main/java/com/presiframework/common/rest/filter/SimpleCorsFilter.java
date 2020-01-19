package com.presiframework.common.rest.filter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este filter autoriza los preflith de los cors request generado por los
 * navegadorres web cuando realiza una peticion a un recurso de otro dominio.
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class SimpleCorsFilter implements Filter {

    private List<String> allowedOrigings;
    private Integer maxAge;
    private String allowedHeaders;
    private String exposedHeaders;

    private static final int DEFAULT_MAX_AGE = 1800; // 1800 segundo = 30 minutos

    public SimpleCorsFilter(List<String> allowedOrigings, String allowedHeaders) {
        this(allowedOrigings, allowedHeaders, DEFAULT_MAX_AGE);
    }

    public SimpleCorsFilter(List<String> allowedOrigings, String allowedHeaders, Integer maxAge) {
        this.allowedOrigings = allowedOrigings;
        this.allowedHeaders = allowedHeaders;
        this.maxAge = maxAge;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {        
        
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("Origin");
        String xforwardedfor = request.getHeader("x-forwarded-for");
        
        System.out.println("SimpleCorsFilter.doFilter() origin = " + origin);
        System.out.println("SimpleCorsFilter.doFilter() xforwardedfor = " + xforwardedfor);
        
        if (xforwardedfor != null && !xforwardedfor.isEmpty()) {
            String[] addressToken = xforwardedfor.split(":");
            xforwardedfor = addressToken != null && addressToken.length > 0? addressToken[0] : xforwardedfor;            
            System.out.println("SimpleCorsFilter.doFilter() xforwardedfor nuevo valor = " + xforwardedfor);
        }
       
        boolean isAllowedOrigin = validarOrigin(origin == null? xforwardedfor : origin);
        String requetedUri = request.getRequestURI();
        String requestedMethod = request.getMethod();
        
        System.out.println("SimpleCorsFilter.doFilter() isAllowedOrigin = " + isAllowedOrigin);
        System.out.println("SimpleCorsFilter.doFilter() requested URI = " + requetedUri);
        System.out.println("SimpleCorsFilter.doFilter() requestedMethod = " + requestedMethod);

       /* if (!isAllowedOrigin) {
            // response unauthorize error
        }
        
        if ("OPTIONS".equalsIgnoreCase(requestedMethod) && isAllowedOrigin) {
            System.out.println("SimpleCorsFilter.doFilter() OPTIONS method allowed for origin ");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", maxAge.toString());
            response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
            
            if (this.exposedHeaders != null && !this.exposedHeaders.isEmpty()) {
                response.setHeader("Access-Control-Expose-Headers", this.exposedHeaders);
            } 
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            chain.doFilter(req, res);
        } */
        
        /*else if (isAllowedOrigin) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", maxAge.toString());
            response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
            
            if (this.exposedHeaders != null && !this.exposedHeaders.isEmpty()) {
                response.setHeader("Access-Control-Expose-Headers", this.exposedHeaders);
            }            
        }*/ 
        
        
        
        
        if (isAllowedOrigin) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", maxAge.toString());
            response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
            
            if (this.exposedHeaders != null && !this.exposedHeaders.isEmpty()) {
                response.setHeader("Access-Control-Expose-Headers", this.exposedHeaders);
            }
        }

        if ("OPTIONS".equalsIgnoreCase(requestedMethod) && isAllowedOrigin) {
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Valida que el origin sea un valor permitido
     *
     * @param origin
     * @return
     */
    private boolean validarOrigin(String origin) {
        if (Objects.isNull(origin) || origin.isEmpty()) {
            return false;
        }

        if (!allowedOrigings.isEmpty()) {

            if (allowedOrigings.size() == 1) {
                String allowedOrigin = allowedOrigings.get(0);

                if (allowedOrigin == null || allowedOrigin.isEmpty()) {
                    return false;
                } else {
                    return "*".equals(allowedOrigin) ? true : allowedOrigin.equals(origin);
                }

            } else {
                return allowedOrigings.contains(origin);
            }
        }

        return false;
    }

    @Override
    public void destroy() {
    }

    public List<String> getAllowedOrigings() {
        return allowedOrigings;
    }

    public String getAllowedHeaders() {
        return allowedHeaders;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public String getExposedHeaders() {
        return exposedHeaders;
    }

    public void setExposedHeaders(String exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }
}
