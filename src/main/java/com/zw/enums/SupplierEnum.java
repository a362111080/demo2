package com.zw.enums;

public class SupplierEnum {

    public enum Status {

        SUCCESS(1,"成功"),INNER_ERROR(-1,"失败");

        private Integer index;
        private String note;

        Status(Integer index,String note){
            this.index = index;
            this.note = note;
        }

        public Integer index() {
            return index;
        }

        public String note() {
            return note;
        }

        public static String note(Integer index){
            Status[] temp = Status.values();
            for(int i=0;i<temp.length;i++){
                Status item = temp[i];
                if(item.index.equals(index)){
                    return item.note;
                }
            }
            return null;
        }

        public static Integer index(String note) {
            Status[] temp = Status.values();
            for(int i=0;i<temp.length;i++){
                Status item = temp[i];
                if(item.note.equals(note)){
                    return item.index;
                }
            }
            return null;
        }
    }
}
