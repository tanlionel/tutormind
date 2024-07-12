package com.exe212.tutormind.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.payos.PayOS;
import com.lib.payos.type.ItemData;
import com.lib.payos.type.PaymentData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class RestCheckOut {
    @Autowired
    private final PayOS payOS;

    public RestCheckOut(PayOS payOS) {
        this.payOS = payOS;
    }


    @PostMapping("create-payment-link")
    public String checkout(@RequestParam int price,HttpServletResponse httpServletResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        String checkoutUrl = "";
        try {
            final String productName = "Tutormind";
            final String description = "Thanh toan don hang";
            final String returnUrl = "https://exe-201-tutor-minds.vercel.app/successpaymentscreen";
            final String cancelUrl = "https://exe-201-tutor-minds.vercel.app/failurepaymentscreen";
            // Gen order code
            String currentTimeString = String.valueOf(new Date().getTime());
            int orderCode = Integer.parseInt(currentTimeString.substring(currentTimeString.length() - 6));
            ItemData item = new ItemData(productName, 1, price);
            List<ItemData> itemList = new ArrayList<>();
            itemList.add(item);
            PaymentData paymentData = new PaymentData(orderCode, price, description,
                    itemList, cancelUrl, returnUrl);
            JsonNode data = payOS.createPaymentLink(paymentData);

            checkoutUrl = data.get("checkoutUrl").asText();

            //httpServletResponse.setHeader("Location", checkoutUrl);
            //httpServletResponse.setStatus(302);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkoutUrl;
    }
}