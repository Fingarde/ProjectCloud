package fr.iut.acc_manager.utils;

import java.util.UUID;

public class IdGenerator {
   public static long generate() {
       long id_int = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

       String id = String.valueOf(id_int).substring(0, 12);
       return Long.parseLong(id);
   }
}
