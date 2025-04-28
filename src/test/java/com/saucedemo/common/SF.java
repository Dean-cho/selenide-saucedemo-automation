package com.saucedemo.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton Factory - 개선 버전
 */
@SuppressWarnings("unchecked")
public class SF {
    private static final Map<String, Object> instances = new ConcurrentHashMap<>();

    public static synchronized <T> T get(Class<T> type) {
        final String typeName = type.getName();
        if (!instances.containsKey(typeName)) {
            createInstance(type);
        }
        return (T) instances.get(typeName);
    }

    private static synchronized <T> void createInstance(Class<T> type) {
        final String typeName = type.getName();
        if (instances.containsKey(typeName)) return;

        try {
            // 기본 생성자 존재 여부 확인
            if (type.getDeclaredConstructors().length == 0 ||
                    type.getConstructors()[0].getParameterCount() > 0) {
                throw new IllegalArgumentException("기본 생성자가 필요합니다: " + typeName);
            }

            T instance = type.getConstructor().newInstance();
            instances.put(typeName, instance);

        } catch (Exception e) {
            throw new RuntimeException("인스턴스 생성 실패: " + typeName, e);
        }
    }

    // 전체 인스턴스 제거
    public static synchronized void clear() {
        instances.clear();
    }

    // 특정 인스턴스만 제거
    public static synchronized void clear(Class<?> type) {
        instances.remove(type.getName());
    }
}
