package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.interfaces.Report;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public abstract class Worker extends User implements Report {

    public int hourlyRate;

    Worker() {
        super();
    }

    Worker(String name) {
        super(name);
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
