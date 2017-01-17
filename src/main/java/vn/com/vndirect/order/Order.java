package vn.com.vndirect.order;

import com.sun.javafx.binding.StringFormatter;

import java.io.Serializable;

public class Order implements Serializable {
    private Long account;
    private int quantity;
    private int price;
    private String orderType;
    private String side;
    private String symbol;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return String.format("{account: %d, quantity: %d, price: %d, " +
                "orderType: %s, side: %s, symbol: %s}",
                account, quantity, price, orderType, side, symbol);
    }
}
