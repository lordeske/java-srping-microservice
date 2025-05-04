package com.booking_service.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;



@Data
public class EventResponse {


 private  Long eventId;
 private  String event;
 private  Long capacity;
 private  VenueResponse venue;
 private  BigDecimal ticketPrice;

}
