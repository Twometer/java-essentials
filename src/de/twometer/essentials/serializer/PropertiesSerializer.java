package de.twometer.essentials.serializer;

import de.twometer.essentials.utils.TextUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesSerializer {

    public static <T> T deserialize(InputStream inputStream, Class<T> objectClass) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        try {
            T object = objectClass.newInstance();
            for (Field field : objectClass.getDeclaredFields()) {
                Property property = field.getAnnotation(Property.class);
                if (property == null)
                    continue;
                String propertyName = TextUtils.isNullOrEmpty(property.value()) ? field.getName() : property.value();
                field.set(object, properties.getProperty(propertyName));
            }
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T deserialize(String path, Class<T> objectClass) throws IOException {
        return deserialize(new FileInputStream(path), objectClass);
    }

}
