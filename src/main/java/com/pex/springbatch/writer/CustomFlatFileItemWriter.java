package com.pex.springbatch.writer;

import java.util.List;

import com.pex.springbatch.model.CustomPojo;
import org.springframework.batch.item.file.FlatFileItemWriter;

public class CustomFlatFileItemWriter extends FlatFileItemWriter<CustomPojo> {

    @Override
    public void write(List<? extends CustomPojo> arg0) throws Exception {
        super.write(arg0);
    }

}
