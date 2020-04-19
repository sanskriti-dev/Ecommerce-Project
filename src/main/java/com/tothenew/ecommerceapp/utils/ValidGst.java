package com.tothenew.ecommerceapp.utils;

import org.springframework.stereotype.Service;

@Service
public class ValidGst {
    public boolean checkGstValid(String gst) {
       if (gst.length() != 15) {
           return false;
       }
       if (gst.charAt(13)!= 'Z') {
           return false;
       }
       return true;
    }
}
