package com.exe212.tutormind.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.payos.PayOS;
import com.lib.payos.type.ItemData;
import com.lib.payos.type.PaymentData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CheckoutController {
  private final PayOS payOS;
  public CheckoutController(PayOS payOS) {
    super();
    this.payOS = payOS;
  }
    @RequestMapping(value = "/")
    public String Index() {
        return "index";
    }
    @RequestMapping(value = "/success")
    public String Success() {
        return "success";
    }
    @RequestMapping(value = "/cancel")
    public String Cancel() {
        return "cancel";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/create-payment-link", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void checkout(HttpServletResponse httpServletResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String productName = "Mì tôm hảo hảo ly";
            final String description = "Thanh toan don hang";
            final String returnUrl = "https://exe-201-tutor-minds.vercel.app/successpaymentscreen";
            final String cancelUrl = "https://exe-201-tutor-minds.vercel.app/failurepaymentscreen";
            final int price = 2000;
            // Gen order code
            String currentTimeString = String.valueOf(new Date().getTime());
            int orderCode = Integer.parseInt(currentTimeString.substring(currentTimeString.length() - 6));
            ItemData item = new ItemData(productName, 1, price);
            List<ItemData> itemList = new ArrayList<>();
            itemList.add(item);
            PaymentData paymentData = new PaymentData(orderCode, price, description,
              itemList, cancelUrl, returnUrl);
            JsonNode data = payOS.createPaymentLink(paymentData);

            String checkoutUrl = data.get("checkoutUrl").asText();

            httpServletResponse.setHeader("Location", checkoutUrl);
            httpServletResponse.setStatus(302);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}