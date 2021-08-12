package kafka;

public class QueryUpdateRecord {
    private Boolean isDelete;
    private ShoppingCartRecord shoppingCartRecord;

    public QueryUpdateRecord() {
    }

    public QueryUpdateRecord(Boolean isDelete, ShoppingCartRecord shoppingCartRecord) {
        this.isDelete = isDelete;
        this.shoppingCartRecord = shoppingCartRecord;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public ShoppingCartRecord getShoppingCartRecord() {
        return shoppingCartRecord;
    }

    public void setShoppingCartRecord(ShoppingCartRecord shoppingCartRecord) {
        this.shoppingCartRecord = shoppingCartRecord;
    }
}
