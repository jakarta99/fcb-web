package tw.com.fcb.sample.cj.web;

import lombok.Data;

@Data
public class VehicleSharing {

    int id;
    int year;
    int month;
    VehicleBrandEnum brand;
    int type;
    int amount;

}
