package by.kachanov.shop.service.converter;

public class ConversionContextHolder {

    private ThreadLocal<Class<?>> currentType = new ThreadLocal<>();

    private ConversionContextHolder() {

    }

    private static class ConversionContextHolderInternal {
        private static ConversionContextHolder INSTANCE = new ConversionContextHolder();
    }

    public static ConversionContextHolder getInstance() {
        return ConversionContextHolderInternal.INSTANCE;
    }

    public void setCurrentType(Class<?> currentType) {
        this.currentType.set(currentType);
    }

    public Class<?> getCurrentType() {
        return this.currentType.get();
    }

}
