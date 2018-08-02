package com.example.appjava.utils;



import com.example.appjava.annonation.ListParamAnnonation;
import com.example.appjava.annonation.NumberParamAnnonation;
import com.example.appjava.annonation.StringParamAnnonation;
import com.example.appjava.exception.ValidateException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class ValidateUtil {

    public static void validate(Object object) throws ValidateException {
        try {
            if (object == null) {
                throw new ValidateException("传入的参数为空");
            } else {
                Class cls = object.getClass();
                Field[] fields = cls.getDeclaredFields();
                validateField(fields, object);
            }
        } catch (IllegalAccessException i) {
            throw new ValidateException("验证位置错误", i);
        }
    }

    private static void validateField(Field[] fields, Object object) throws ValidateException, IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Class fieldClass = field.getType();
            if (String.class.isAssignableFrom(fieldClass)) {
                validateString(field, object);
            } else if (Number.class.isAssignableFrom(fieldClass)) {
                validateNumber(field, object);
            } else if (Collection.class.isAssignableFrom(fieldClass)) {
                validateList(field, object);
            }
        }
    }

    public static void validateNumber(Field field, Object object) throws ValidateException, IllegalAccessException {
        NumberParamAnnonation na = (NumberParamAnnonation) field.getAnnotation(NumberParamAnnonation.class);
        if (null != na) {
            String message;
            if (!na.canBeNull() && (field.get(object) == null || "".equals(field.get(object)))) {
                message = "字段" + field.getName() + "为空";
                throw new ValidateException(message);
            }
        }
    }

    public static void validateString(Field field, Object object) throws ValidateException, IllegalAccessException {
        StringParamAnnonation sa = (StringParamAnnonation) field.getAnnotation(StringParamAnnonation.class);
        if (null != sa) {
            String message;
            if (!sa.canBeNull() && (null == field.get(object) || "".equals(field.get(object)))) {
                message = "字段" + field.getName() + "为空";
                throw new ValidateException(message);
            }
        }
    }

    public static void validateList(Field field, Object object) throws ValidateException, IllegalAccessException {
        ListParamAnnonation la = (ListParamAnnonation) field.getAnnotation(ListParamAnnonation.class);
        if (la != null) {
            String message;
            if (!la.canBeNull() && (null == field.get(object) || ((List) field.get(object)).equals(0))) {
                message = "字段" + field.getName() + "为空";
                throw new ValidateException(message);
            }
        }
    }
}
