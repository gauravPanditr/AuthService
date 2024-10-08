package org.example.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.eventProducer.UserInfoEvent;
import org.example.model.UserInfoDto;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoEvent> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String arg0, UserInfoEvent agr1) {
        byte[] retVal=null;
         ObjectMapper objectMapper=new ObjectMapper();
        try {
            retVal= objectMapper.writeValueAsString(agr1).getBytes();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return retVal;
    }



    @Override
    public void close() {

    }
}
