package galen.pages.Checkout;

import galen.pages.common.GalenPageObj;
import galen.pages.tenant.dexter.InitialAssessment.*;
import org.openqa.selenium.WebDriver;

public class CheckoutPageObj extends GalenPageObj {
    public OrderConfirmation orderConfirmation;
    public PurchaseOptions purchaseOptions;
    public PaymentInfo paymentInfo;
    public PaymentMethod paymentMethod;
    public PreviewOrder previewOrder;
    public Product product;
    public PurchasePage purchase;
    public ShippingAddress shippingAddress;
    public ShippingMethod shippingMethod;

    public CheckoutPageObj(WebDriver driver) {
        super(driver);
        orderConfirmation = new OrderConfirmation(driver);
        paymentInfo = new PaymentInfo(driver);
        paymentMethod = new PaymentMethod(driver);
        purchaseOptions = new PurchaseOptions(driver);
        previewOrder = new PreviewOrder(driver);
        product = new Product(driver);
        purchase = new PurchasePage(driver);
        shippingAddress = new ShippingAddress(driver);
        shippingMethod = new ShippingMethod(driver);
    }
}
