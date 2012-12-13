package cz.cvut.fel.via.zboziforandroid.client.premise;

public class EshopPremise {

    private String[] paymentType;
    private int id;
    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EshopPremise{" + "id=" + id + ", name=" + name + '}');
                
        sb.append("\n");
        for (int i = 0; i < paymentType.length; i++) {
            sb.append(paymentType[i]).append(", ");
        }
        sb.delete(sb.length()-2, sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }
    

}
