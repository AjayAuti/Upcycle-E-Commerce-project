package com.upcycle.Services;

import com.upcycle.Entity.Payment;

public interface PaymentService {
	Payment savePayment(Payment payment);
	Payment findPaymentById(int id);
}
