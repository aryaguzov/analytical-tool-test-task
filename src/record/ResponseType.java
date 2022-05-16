package record;

public enum ResponseType {
    P,
    N;

    public static ResponseType convert(String responseType) {
        return responseType.equals("P") ? P : N;
    }
}
