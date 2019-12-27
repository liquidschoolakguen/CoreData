package akguen.liquidschool.coredata.model;

public class Device {



    private String uuid;




    public Device(String uuid){

        this.uuid = uuid;
    }



    public Device(){


    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
