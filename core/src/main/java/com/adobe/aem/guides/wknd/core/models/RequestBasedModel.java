package com.adobe.aem.guides.wknd.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

/**
 * Example Sling Model that adapts from a SlingHttpServletRequest
 * This is typically used when you need access to request parameters, headers,
 * or other request-specific information
 */
@Model(
    adaptables = SlingHttpServletRequest.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RequestBasedModel {
    
    /**
     * Self-reference to the request this model has adapted from
     */
    @Self
    private SlingHttpServletRequest request;
    
    /**
     * The current resource associated with the request
     */
    @SlingObject
    private Resource currentResource;
    
    /**
     * Access to properties of the current resource
     * Note: We can still access resource properties in a request-based model
     */
    @ValueMapValue
    private String title;
    
    /**
     * Access request parameters
     * For example, accessing ?param=value from the URL
     */
    private String queryParameter;
    
    /**
     * Demonstrates accessing a script variable that would be available in the HTL context
     * For example, properties that come from data-sly-use.product="${'com.example.Product' @ param='value'}"
     */
    @ScriptVariable(name="properties")
    private Object pageProperties;
    
    /**
     * Post-construct initialization method
     */
    @PostConstruct
    protected void init() {
        // Access request parameters in the init method
        queryParameter = request.getParameter("param");
    }
    
    /**
     * @return the title from the resource
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the query parameter from the request
     */
    public String getQueryParameter() {
        return queryParameter;
    }
    
    /**
     * @return the path of the current resource
     */
    public String getCurrentResourcePath() {
        return currentResource != null ? currentResource.getPath() : null;
    }
    
    /**
     * Example method demonstrating how you might use the request object
     * @return the user agent string from the request
     */
    public String getUserAgent() {
        return request.getHeader("User-Agent");
    }
}
