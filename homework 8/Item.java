public class Item<T> {
    private Long time;
    private T content;

    public Item(T t) {
        this.time = System.currentTimeMillis();
        this.content = t;
    }
    
    public T getContent() {
        return content;
    }

    public Long getTime() {
        return time;
    }
}
