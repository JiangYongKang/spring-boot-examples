# google-guice-demo

### 绑定
通过类名绑定
```java
bind(PriceService.class).to(PriceServiceImp.class);
```
通过实例绑定
```java
bind(PriceService.class).toInstance(new PriceServiceImp());
```
匿名函数连接绑定
```java
bind(PriceService.class).toInstance(new PriceServiceImp() {
    @Override
    public long getPrice(long orderId) {
        return 100;
    }
});
```