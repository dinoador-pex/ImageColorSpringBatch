package com.pex.springbatch.writer;

import com.pex.springbatch.model.CustomPojo;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

public class CustomDelimitedAggregator extends DelimitedLineAggregator<CustomPojo> {

    @Override
    public String doAggregate(Object[] fields) {
        return super.doAggregate(fields);
    }

}
