package com.github.lyokofirelyte.WaterCloset.Util;

// CREDIT TO LazyLemons FROM BUKKIT FOR THIS CODE!!!
// https://forums.bukkit.org/threads/get-server-tps.143410/

public class WCTPS {
	
	public int getTPS(){
	
	int tps = 0;
    long sec;
    long currentSec = 0;
    int  ticks = 0;
  
        sec = (System.currentTimeMillis() / 1000);
       
        if(currentSec == sec)
        {
            ticks++;
        }
        else
        {
            currentSec = sec;
            tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
            ticks = 0;
            return tps;
        }
		return tps;
    }
}

