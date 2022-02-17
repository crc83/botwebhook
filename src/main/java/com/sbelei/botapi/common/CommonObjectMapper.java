package com.sbelei.botapi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonObjectMapper extends ObjectMapper {

    public CommonObjectMapper() {
//        registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    Logger LOG = LoggerFactory.getLogger(CommonObjectMapper.class);

    public String toJson(Object object) {
        try {
            return writeValueAsString(object);
        } catch (JsonProcessingException e) {
            if (object != null) {
                LOG.error("Error processing json for object:" + object.toString(), e);
            } else {
                LOG.error("Error processing json null", e);
            }
        }
        return "";
    }
}
