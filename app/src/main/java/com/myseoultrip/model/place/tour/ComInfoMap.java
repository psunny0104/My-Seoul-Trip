package com.myseoultrip.model.place.tour;

import java.util.HashMap;

public class ComInfoMap {
    private HashMap<String, String> comInfoMap = new HashMap<>();

    private final String[] comInfoKey = {  //관광지
            "chkbabycarriage", "chkcreditcard", "chkpet", "expguide", "heritage1",
            "heritage2", "heritage3", "infocenter", "opendate", "parking",
            "restdate", "useseason", "usetime",
            //문화시설
            "accomcountculture", "chkbabycarriageculture", "chkcreditcardculture", "chkpetculture", "discountinfo",
            "infocenterculture", "parkingculture", "parkingfee", "restdateculture", "scale",
            "usefee", "usetimeculture", "spendtime",
            //축제/공연/행사
            "agelimit", "bookingplace", "discountinfofestival", "eventenddate", "eventhomepage",
            "eventplace", "eventstartdate", "placeinfo", "playtime", "program",
            "spendtimefestival", "sponsor1", "sponsor1tel", "sponsor2", "sponsor2tel",
            "subevent", "usetimefestival",
            //음식점
            "chkcreditcardfood", "discountinfofood", "firstmenu", "infocenterfood", "kidsfacility",
            "opendatefood", "opentimefood", "packing", "parkingfood", "reservationfood",
            "restdatefood", "scalefood", "seat", "smoking", "treatmenu",
            //쇼핑
            "chkbabycarriageshopping", "chkcreditcardshopping","chkpetshopping", "culturecenter",
            "fairday","infocentershopping","opendateshopping", "opentime","parkingshopping","restdateshopping",
            "restroom", "saleitem","saleitemcost","scaleshopping","shopguide",

            //레포츠
            "accomcountleports","chkbabycarriage","chkcreditcard","chkpet","expagerangeleports","infocenterleports",
            "openperiod","parkingfeeleports","parkingleports","restdateleports","scaleleports",
            "usefeeleports","usetimeleports"

    };

    private final String[] comInfoValue = { //관광지
            "Stroller rental status", "Credit card availability", "Pets availability",
            "Experience guide", "World Heritage status1",
            "World Heritage status2", "World Heritage status3", "Inquiry and guidance", "Opening day", "Parking facilities",
            "Off day", "Time of use", "Hours of use",
            //문화시설
            "Capacity", "Stroller rental status", "Credit card availability", "Pets availability", "Discount information",
            "Inquiry and guidance", "parking facilities", "Parking fee", "off day", "Scale",
            "Usage fee", "Hours of use", "Time required",
            //축제/공연/행사
            "Viewing age", "Reservation", "Discount information", "Event end date", "Event homepage",
            "Event venue", "Event start date", "Event location information", "Performance time", "Event program",
            "Time required", "Organizer information", "Organizer Contact", "Organizer information", "Organizer Contact",
            "Side event", "Usage fee",
            //음식점
            "Credit card availability", "Discount information", "Main menu", "Inquiry and guidance", "Children's playroom",
            "Opening day", "Opening hours", "Packing availability", "parking facilities", "Reservation information",
            "off day", "Scale", "Seating", "Non-smoking/smoking", "Handling menu",
            //쇼핑
            "Stroller rental status", "Credit card availability","Pets availability", "Culture center",
            "Fairday","Inquiry and guidance","Opening day", "Opening hours","parking facilities","Off day",
            "Restroom", "Sale item","Price by item sold","Scale","Shop guide",
            //레포츠
            "Capacity","Stroller rental status","Credit card availability","Pets availability", "Experience age","Inquiry and guidance",
            "Opening period","Parking fee","Parking facilities","Booking guidance","Scale",
            "Admission fee","Hours of use"

    };



    public void init()
    {
        for(int i = 0; i<comInfoKey.length; i++){
            comInfoMap.put(comInfoKey[i], comInfoValue[i]);
        }
    }

    public HashMap<String, String> getComInfoMap() {
        return comInfoMap;
    }

    public void setComInfoMap(HashMap<String, String> comInfoMap) {
        this.comInfoMap = comInfoMap;
    }

    public String[] getComInfoKey() {
        return comInfoKey;
    }

    public String[] getComInfoValue() {
        return comInfoValue;
    }
}