package com.haier.smarthomesdk.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

/**
 * Created on 2018/8/29.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AttributesMap {
    private static ArrayMap<String, ArrayMap<String, String>> attributesMap = new ArrayMap<>();

    static {
        ArrayMap<String, String> refrigerator_model_config = new ArrayMap<>();
        refrigerator_model_config.put("0", "Bottom Freezer (French Door)");
        refrigerator_model_config.put("1", "Bottom Freezer (One Door)");
        refrigerator_model_config.put("2", "Side by Side");
        refrigerator_model_config.put("3", "Single Cabinet Fresh Food");
        refrigerator_model_config.put("4", "Single Cabinet Freezer");
        refrigerator_model_config.put("5", "Top Freezer");
        refrigerator_model_config.put("6", "Convertible Drawer");
        attributesMap.put("refrigerator-model-config", refrigerator_model_config);

        ArrayMap<String, String> dishwasher_cycle_state = new ArrayMap<>();
        dishwasher_cycle_state.put("0", "CNo Change");
        dishwasher_cycle_state.put("1", "PreWash");
        dishwasher_cycle_state.put("2", "Sensing");
        dishwasher_cycle_state.put("3", "MainWash");
        dishwasher_cycle_state.put("4", "Drying");
        dishwasher_cycle_state.put("5", "Sanitizing");
        dishwasher_cycle_state.put("6", "TurbidityCal");
        dishwasher_cycle_state.put("7", "DiverterCal");
        dishwasher_cycle_state.put("8", "Pause");
        dishwasher_cycle_state.put("9", "Rinsing");
        dishwasher_cycle_state.put("10", "PreWash 1");
        dishwasher_cycle_state.put("11", "Final Rinse");
        dishwasher_cycle_state.put("12", "End PreWash 1");
        dishwasher_cycle_state.put("13", "Auto HotStart 1");
        dishwasher_cycle_state.put("14", "Auto HotStart 2");
        dishwasher_cycle_state.put("15", "Auto HotStart 3");
        dishwasher_cycle_state.put("16", "Final Rinse Fill");
        dishwasher_cycle_state.put("17", "Cycle Inactive");
        dishwasher_cycle_state.put("18", "Max");
        attributesMap.put("dishwasher-cycle-state", dishwasher_cycle_state);

        ArrayMap<String, String> dishwasher_selected_cycle = new ArrayMap<>();
        dishwasher_selected_cycle.put("0", "AutoSense");
        dishwasher_selected_cycle.put("2", "Heavy");
        dishwasher_selected_cycle.put("4", "Normal");
        dishwasher_selected_cycle.put("6 ", "Light");
        dishwasher_selected_cycle.put("8", "Express");
        dishwasher_selected_cycle.put("10", "eWash");
        dishwasher_selected_cycle.put("12", "Rinse");
        dishwasher_selected_cycle.put("32", "AutoSense");
        dishwasher_selected_cycle.put("34", "Heavy");
        dishwasher_selected_cycle.put("36", "Normal");
        dishwasher_selected_cycle.put("38", "Light");
        dishwasher_selected_cycle.put("40", "Express");
        dishwasher_selected_cycle.put("42", "eWash");
        dishwasher_selected_cycle.put("44", "Rinse");
        attributesMap.put("dishwasher-selected-cycle", dishwasher_selected_cycle);

        ArrayMap<String, String> ishwasher_operating_mode = new ArrayMap<>();
        ishwasher_operating_mode.put("0", "Low Power");
        ishwasher_operating_mode.put("1", "Power Up");
        ishwasher_operating_mode.put("2", "Standby");
        ishwasher_operating_mode.put("3", "Delay Start");
        ishwasher_operating_mode.put("4", "Pause");
        ishwasher_operating_mode.put("5", "Cycle Active");
        ishwasher_operating_mode.put("6", "EOC");
        ishwasher_operating_mode.put("7", "Download Mode");
        ishwasher_operating_mode.put("8", "Sensor Check Mode");
        ishwasher_operating_mode.put("9", "Load Activation Mode");
        ishwasher_operating_mode.put("10", "MC Only Mode");
        ishwasher_operating_mode.put("11", "Warming Mode");
        ishwasher_operating_mode.put("12", "Control Locked");
        ishwasher_operating_mode.put("13", "CSM Tripped");
        attributesMap.put("dishwasher-operating-mode", ishwasher_operating_mode);

        ArrayMap<String, String> microwave_state_cook_mode = new ArrayMap<>();
        microwave_state_cook_mode.put("0", "No Mode");
        microwave_state_cook_mode.put("1", "Timed");
        microwave_state_cook_mode.put("2", "Popcorn");
        microwave_state_cook_mode.put("3", "Potato");
        microwave_state_cook_mode.put("4", "Frozen Vegetables");
        microwave_state_cook_mode.put("5", "Fresh Vegetables");
        microwave_state_cook_mode.put("6", "Pizza");
        microwave_state_cook_mode.put("7", "Reheat");
        microwave_state_cook_mode.put("8", "Dinner Plate");
        microwave_state_cook_mode.put("9", "Beverage");
        microwave_state_cook_mode.put("10", "Defrost by Weight");
        microwave_state_cook_mode.put("11", "Defrost by Time");
        attributesMap.put("microwave-state-cook-mode", microwave_state_cook_mode);

        ArrayMap<String, String> range_lower_cook_mode = new ArrayMap<>();
        range_lower_cook_mode.put("0", "Off");
        range_lower_cook_mode.put("1", "Bake No Option");
        range_lower_cook_mode.put("2", "Bake Probe");
        range_lower_cook_mode.put("3", "Bake Delay Start");
        range_lower_cook_mode.put("4", "Bake Timed Warm");
        range_lower_cook_mode.put("5", "Bake Timed Two Temp");
        range_lower_cook_mode.put("6", "Bake Probe Delay Start");
        range_lower_cook_mode.put("7", "Bake Timed Shutoff Delay Start");
        range_lower_cook_mode.put("8", "Bake Timed Warm Delay Start");
        range_lower_cook_mode.put("9", "Bake Timed Two Temp Delay Start");
        range_lower_cook_mode.put("10", "Bake Sabbath");

        range_lower_cook_mode.put("11", "Broil High");
        range_lower_cook_mode.put("12", "Broid Low");
        range_lower_cook_mode.put("13", "Proof No Option");
        range_lower_cook_mode.put("14", "Proof Delay Start");
        range_lower_cook_mode.put("15", "Warm No Option");
        range_lower_cook_mode.put("16", "Warm Probe");
        range_lower_cook_mode.put("17", "Warm Delay Start");
        range_lower_cook_mode.put("18", "Conv Bake No Option");
        range_lower_cook_mode.put("19", "Conv Bake Probe");
        range_lower_cook_mode.put("20", "Conv Bake Delay Start");

        range_lower_cook_mode.put("21", "Conv Bake Timed Warm");
        range_lower_cook_mode.put("22", "Conv Bake Timed Two Temp");
        range_lower_cook_mode.put("23", "Conv Bake Probe Delay Start");
        range_lower_cook_mode.put("24", "Conv Bake Timed Shutoff Delay Start");
        range_lower_cook_mode.put("25", "Conv Bake Timed Warm Delay Start");
        range_lower_cook_mode.put("26", "Conv Bake Timed Two Temp Delay Startn");
        range_lower_cook_mode.put("27", "Conv Multi Bake No Option");
        range_lower_cook_mode.put("28", "Conv Multi Bake Probe");
        range_lower_cook_mode.put("29", "Conv Multi Bake Delay Start");
        range_lower_cook_mode.put("30", "Conv Multi Bake Timed Warm");

        range_lower_cook_mode.put("31", "Conv Multi Bake Timed Two Temp");
        range_lower_cook_mode.put("32", "Conv Multi Bake Probe Delay Start");
        range_lower_cook_mode.put("33", "Conv Multi Bake Timed Shutoff Delay Start");
        range_lower_cook_mode.put("34", "Conv Multi Bake Timed Warm Delay Start");
        range_lower_cook_mode.put("35", "Conv Multi Bake Timed Two Temp Delay Start");
        range_lower_cook_mode.put("36", "Conv Roast No Option");
        range_lower_cook_mode.put("37", "Conv Roast Probe");
        range_lower_cook_mode.put("38", "Conv Roast Delay Start");
        range_lower_cook_mode.put("39", "Conv Roast Timed Warm");
        range_lower_cook_mode.put("40", "Conv Roast Timed Two Temp");

        range_lower_cook_mode.put("41", "Conv Roast Probe Delay Start");
        range_lower_cook_mode.put("42", "Conv Roast Timed Shutoff Delay Start");
        range_lower_cook_mode.put("43", "Conv Roast Timed Warm Delay Start");
        range_lower_cook_mode.put("44", "Conv Roast Timed Two Temp Delay Start");
        range_lower_cook_mode.put("45", "Conv Broil Low No Option");
        range_lower_cook_mode.put("46", "Conv Broil High No Option");
        range_lower_cook_mode.put("47", "Conv Broil Crisp No Option");
        range_lower_cook_mode.put("48", "Conv Broil Crisp Probe");
        range_lower_cook_mode.put("49", "Custom Self Clean");
        range_lower_cook_mode.put("50", "Custom Self Clean Delay Start");

        range_lower_cook_mode.put("51", "Steam Clean");
        range_lower_cook_mode.put("52", "Steam Clean Delay Start");
        range_lower_cook_mode.put("53", "Dual Broil Low No Option");
        range_lower_cook_mode.put("54", "Dual Broil High No Option");
        range_lower_cook_mode.put("55", "FCT No Option");
        range_lower_cook_mode.put("56", "Frozen Snacks No Option");
        range_lower_cook_mode.put("57", "Frozen Snacks Multi NoOption");
        range_lower_cook_mode.put("58", "Frozen Pizza No Option");
        range_lower_cook_mode.put("59", "Frozen Pizza Multi No Option");
        range_lower_cook_mode.put("60", "Baked Goods No Option");

        range_lower_cook_mode.put("61", "rozen Snacks Delay Start");
        range_lower_cook_mode.put("62", "Frozen Snacks Multi Delay Start");
        range_lower_cook_mode.put("63", "Frozen Pizza Delay Start");
        range_lower_cook_mode.put("64", "Frozen Pizza Multi Delay Start");
        range_lower_cook_mode.put("65", "Baked Goods Delay Start");
        attributesMap.put("range-lower-cook-mode", range_lower_cook_mode);
        attributesMap.put("range-upper-cook-mode", range_lower_cook_mode);

        ArrayMap<String, String> laundry_machine_sub_status = new ArrayMap<>();
        laundry_machine_sub_status.put("0", "N/A");
        laundry_machine_sub_status.put("1", "Fill");
        laundry_machine_sub_status.put("2", "Soak");
        laundry_machine_sub_status.put("3", "Wash");
        laundry_machine_sub_status.put("4", "Rinse");
        laundry_machine_sub_status.put("5", "Spin");
        laundry_machine_sub_status.put("6", "Drain");
        laundry_machine_sub_status.put("7", "ExtraSpin");
        laundry_machine_sub_status.put("8", "ExtraRinse");
        laundry_machine_sub_status.put("9", "Tumble");
        laundry_machine_sub_status.put("10", "LoadSizeDetection");
        laundry_machine_sub_status.put("128", "Drying");
        laundry_machine_sub_status.put("129", "MistSteam");
        laundry_machine_sub_status.put("130", "CoolDown");
        laundry_machine_sub_status.put("131", "ExtendedTumble");
        laundry_machine_sub_status.put("132", "Damp");
        laundry_machine_sub_status.put("133", "AirFluff");
        attributesMap.put("laundry-machine-sub-status", laundry_machine_sub_status);


        ArrayMap<String, String> laundry_machine_status = new ArrayMap<>();
        laundry_machine_status.put("0", "Idle");
        laundry_machine_status.put("1", "Standby");
        laundry_machine_status.put("2", "Run");
        laundry_machine_status.put("3", "Pause");
        laundry_machine_status.put("4", "EOC");
        laundry_machine_status.put("5", "DSMDelayRun");
        laundry_machine_status.put("6", "DelayRun");
        laundry_machine_status.put("7", "DelayPause");
        laundry_machine_status.put("8", "DrainTimeout");
        laundry_machine_status.put("128", "CleanSpeak");
        attributesMap.put("laundry-machine-status", laundry_machine_status);


        ArrayMap<String, String> laundry_cycle_selected = new ArrayMap<>();
        laundry_cycle_selected.put("0", "Not Defined");
        laundry_cycle_selected.put("1", "Basket Clean");
        laundry_cycle_selected.put("2", "Drain and Spin");
        laundry_cycle_selected.put("3", "Quick Rinse");
        laundry_cycle_selected.put("4", "Bulky Items");
        laundry_cycle_selected.put("5", "Sanitize");
        laundry_cycle_selected.put("6", "Towels/Sheets");
        laundry_cycle_selected.put("7", "Steam Refresh");
        laundry_cycle_selected.put("8", "Normal/Mixed load");
        laundry_cycle_selected.put("9", "Whites");
        laundry_cycle_selected.put("10", "Dark Colors");
        laundry_cycle_selected.put("11", "Jeans");
        laundry_cycle_selected.put("12", "Hand Wash");
        laundry_cycle_selected.put("13", "Delicates");
        laundry_cycle_selected.put("14", "Speed Wash");
        laundry_cycle_selected.put("15", "Heavy Duty");
        laundry_cycle_selected.put("16", "Allergen");
        laundry_cycle_selected.put("17", "Power Clean");
        laundry_cycle_selected.put("18", "Rinse and Spin");
        laundry_cycle_selected.put("19", "Single Item Wash");
        laundry_cycle_selected.put("20", "Colors");
        laundry_cycle_selected.put("21", "Cold Wash");
        laundry_cycle_selected.put("22", "Water On Demand");
        laundry_cycle_selected.put("128", "Cottons");
        laundry_cycle_selected.put("129", "Easy Care");
        laundry_cycle_selected.put("130", "Active Wear");
        laundry_cycle_selected.put("131", "Timed Dry");
        laundry_cycle_selected.put("132", "DeWrinkle");
        laundry_cycle_selected.put("133", "Quick/Air Fluff");
        laundry_cycle_selected.put("134", "Steam Refresh");
        laundry_cycle_selected.put("135", "Steam Dewrinkle");
        laundry_cycle_selected.put("136", "Speed Dry");
        laundry_cycle_selected.put("137", "Mixed");
        laundry_cycle_selected.put("138", "Quick Dry");
        laundry_cycle_selected.put("139", "Casuals");
        laundry_cycle_selected.put("140", "Warm Up");
        laundry_cycle_selected.put("141", "Energy Saver");
        laundry_cycle_selected.put("142", "Antibacterial");
        attributesMap.put("laundry-cycle-selected", laundry_cycle_selected);
    }

    public static String getDescription(String key, String vlaue) {
        String description = vlaue;
        try {
            description = attributesMap.get(key).get(vlaue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return description;
    }
}
