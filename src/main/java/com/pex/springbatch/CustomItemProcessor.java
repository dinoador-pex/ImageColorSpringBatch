package com.pex.springbatch;

import com.pex.springbatch.model.CustomPojo;
import org.springframework.batch.item.ItemProcessor;

import java.io.IOException;
import java.util.Arrays;

public class CustomItemProcessor implements ItemProcessor<CustomPojo, CustomPojo> {

    @Override
    public CustomPojo process(final CustomPojo pojo) throws IOException {
        final String url = pojo.getUrl();
        String[] csvLine;
        csvLine = ColorFinder.find(url);
        csvLine = add2BeginningOfArray(csvLine, url);
        return new CustomPojo(csvLine);
    }

    public static <T> T[] add2BeginningOfArray(T[] elements, T element) {
        T[] newArray = Arrays.copyOf(elements, elements.length + 1);
        newArray[0] = element;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        return newArray;
    }

}