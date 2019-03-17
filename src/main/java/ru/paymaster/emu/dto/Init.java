package ru.paymaster.emu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Init {
    @JsonProperty("LMI_INVOICE_CONFIRMATION_URL")
    private String lmiInvoiceConfirmationUrl;
    @JsonProperty("LMI_PAYMENT_NOTIFICATION_URL")
    private String lmiPaymentNotificationUrl;
    @JsonProperty("LMI_MERCHANT_ID")
    private String lmiMerchantId;//	Идентификатор сайта в системе PayMaster
    @JsonProperty("LMI_PAYMENT_AMOUNT")
    private String lmiPaymentAmount;//	Сумма платежа, заказанная продавцом. Дробное число с разделителем ".", не более 2 знаков после точки.
    @JsonProperty("LMI_CURRENCY")
    private String lmiCurrency	;//Валюта платежа, заказанная продавцом.
    @JsonProperty("LMI_PAID_AMOUNT")
    private String lmiPaidAmount;//	Сумма платежа в валюте, в которой покупатель производит платеж. Дробное число с разделителем ".", не более 2 знаков после точки.
    @JsonProperty("LMI_PAID_CURRENCY")
    private String LMI_PAID_CURRENCY;//	Валюта, в которой производится платеж. Строковый код валюты (не обязательно ISO).
    @JsonProperty("LMI_PAYMENT_METHOD")
    private String LMI_PAYMENT_METHOD;//	Идентификатор платежного метода, выбранный пользователем.
    @JsonProperty("LMI_SIM_MODE")
    private String LMI_SIM_MODE	;//Флаг тестового режима. Это поле присутствует только если платеж производится в тестовом режиме. Значения - те же, что и в форме заказа платежа.
    @JsonProperty("LMI_PAYMENT_DESC")
    private String LMI_PAYMENT_DESC;//	Описание платежа, как оно показывается пользователю. То есть, если в форме заказа платежа было указано LMI_PAYMENT_DESC64, то в этом запросе придет уже раскодированное из Base64 описание.
    
    //Дополнительные параметры продавца	Определяются продавцом. Сюда входят все дополнительные поля формы заказа платежа.
}
