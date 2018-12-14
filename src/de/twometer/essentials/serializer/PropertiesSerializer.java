package de.twometer.essentials.serializer;

import de.twometer.essentials.utils.TextUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesSerializer {

    public static Properties serialize(Object object) {
        Properties properties = new Properties();
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            String propertyName = getPropertyName(field);
            if (propertyName == null)
                continue;
            try {
                properties.setProperty(propertyName, field.get(object).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static <T> T deserialize(InputStream inputStream, Class<T> objectClass) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        try {
            T object = objectClass.newInstance();
            for (Field field : objectClass.getDeclaredFields()) {
                String propertyName = getPropertyName(field);
                if (propertyName == null)
                    continue;
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

    private static String getPropertyName(Field field) {
        Property property = field.getAnnotation(Property.class);
        if (property == null)
            return null;
        return TextUtils.isNullOrEmpty(property.value()) ? field.getName() : property.value();
    }

}
