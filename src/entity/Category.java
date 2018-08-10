package entity;

public class Category {
    public int id;
    public String name;
    public int recordNumber;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public String toString(){
        return name;
    }
}
