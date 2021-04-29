package ir.co.isc.salesorder;

public enum StatusCodes {
OK(0),SYSTEM_ERROR(401),REQUEST_PROCESSING_ERROR(1);

private int code;
    StatusCodes(int i) {
        this.code=i;
    }
}
