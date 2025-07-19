package com.adobe.aem.guides.wknd.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Example Sling Model that adapts from a Resource
 * This is typically used when you need to access properties of a specific resource/node
 */
@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ResourceBasedModel {
    
    /**
     * This injects the value from the resource property named "title"
     * If the property doesn't exist, it remains null (since we're using OPTIONAL strategy)
     */
    @ValueMapValue
    private String title;
    
    /**
     * This injects the value from the resource property named "description"
     */
    @ValueMapValue
    private String description;
    
    /**
     * Property with a different name from the resource property
     * The value will be taken from the "jcr:created" property
     */
    @ValueMapValue(name = "jcr:created")
    private String createdDate;
    
    /**
     * Post-construct initialization method
     */
    @PostConstruct
    protected void init() {
        // Additional initialization logic if needed
    }
    
    /**
     * @return the title from the resource
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the description from the resource
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @return the created date from the resource
     */
    public String getCreatedDate() {
        return createdDate;
    }
}
