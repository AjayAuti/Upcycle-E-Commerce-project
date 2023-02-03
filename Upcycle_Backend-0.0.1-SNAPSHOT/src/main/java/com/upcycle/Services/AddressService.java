package com.upcycle.Services;

import com.upcycle.Entity.Address;

public interface AddressService {
	Address saveAddress(Address address);
	Address findAddress(int id);
}
