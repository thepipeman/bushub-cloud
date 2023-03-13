package com.bushub.commons.trip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record CustomerBookedTrip(String referenceNumber,
                                 String bookingStatus,
                                 BigDecimal fare,
                                 LocalDate departureDate,
                                 LocalTime departureTime,
                                 String tripStatus,
                                 String busType,
                                 String origin,
                                 String destination,
                                 int distance,
                                 String tripCode) {}