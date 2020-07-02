package hc.test.web.util;

import java.util.UUID;

public class UUIDUtil {

public static String get32UUID() {
    UUID uuid = UUID.randomUUID();
    String uidString = uuid.toString().replace("-", "");
    return uidString.toUpperCase();
}

public static String get36UUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().toUpperCase();
}

/*public static void main(String[] args) {
    String uuid = get32UUID();
    System.out.println(uuid);
    System.out.println(uuid.length());
    uuid = get36UUID();
    System.out.println(uuid);
    System.out.println(uuid.length());
}*/


}
