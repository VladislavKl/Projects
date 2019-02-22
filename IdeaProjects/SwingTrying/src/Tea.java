public class Tea extends Drink {

    @Override
    public String toString() {
        return super.toString()+"; тип чая: "+getTeaType();
    }

    public String getTeaType() {
        return TeaType;
    }

    public void setTeaType(String teaType) {
        TeaType = teaType;
    }

    private String TeaType;
}
