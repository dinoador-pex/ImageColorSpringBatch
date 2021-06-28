package com.pex.springbatch.writer;

import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class CSVQuotingBeanWrapperFieldExtractor<T> implements FieldExtractor<T>, InitializingBean {

    private String[] names;

    /**
     * @param names field names to be extracted by the {@link #extract(Object)} method.
     */
    public void setNames(final String[] names) {
        Assert.notNull(names, "Names must be non-null");
        this.names = names.clone();
    }

    /**
     * @see org.springframework.batch.item.file.transform.FieldExtractor#extract(java.lang.Object)
     */
    @Override
    public Object[] extract(final T item) {
        final List<Object> values = new ArrayList<>();

        final BeanWrapper bw = new BeanWrapperImpl(item);
        for (final String propertyName : this.names) {
            if (bw.getPropertyType(propertyName).isAssignableFrom(String.class)) {
                values.add(doublequoteIfString(bw.getPropertyValue(propertyName)));
            } else {
                values.add(bw.getPropertyValue(propertyName));
            }
        }
        return values.toArray();
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(names, "The 'names' property must be set.");
    }

    private String quote(final String str) {
        return str != null ? "\"" + str + "\"" : null;
    }

    private Object doublequoteIfString(final Object obj) {
        return obj instanceof String ? quote((String) obj) : obj;
    }
}
