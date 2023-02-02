package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Address;

public interface AddressDao {

    public void addAddress(Address newAddress, String stateName);

    public void updateAddress(Address updatedAddress);
    public void removeAddress(int addressId);


}
