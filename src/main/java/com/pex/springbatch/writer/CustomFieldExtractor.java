package com.pex.springbatch.writer;

import com.pex.springbatch.model.CustomPojo;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;

public class CustomFieldExtractor extends BeanWrapperFieldExtractor<CustomPojo> {

    @Override
    public Object[] extract(CustomPojo pojo) {
        System.out.println("extract: " + pojo);
        Object[] results = super.extract(pojo);
        return results;
    }

}
