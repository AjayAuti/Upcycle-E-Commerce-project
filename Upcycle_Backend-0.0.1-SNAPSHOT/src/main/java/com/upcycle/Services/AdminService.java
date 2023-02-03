package com.upcycle.Services;

import com.upcycle.Entity.Admin;

public interface AdminService {
	Admin validate(String userid,String pwd);
	void updateAdmin(Admin amin);
}
