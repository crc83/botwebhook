package com.sbelei.botapi.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonObjectMapper extends ObjectMapper {

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
