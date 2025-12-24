package src.experiments;
public class Dog {

    public int WeightInPounds;
    public static String binomen = "Canis familiaris";

    public Dog(int w){
        WeightInPounds = w;
    }

    public void makeNoise(){
        if (WeightInPounds < 10) {
            System.out.println("Yip!");
        }else if (WeightInPounds < 30) {
            System.out.println("bark!");
            
        }else{
            System.out.println("woooof!");
        }
        
    }

    public static Dog maxDog(Dog d1, Dog d2){
        if(d1.WeightInPounds > d2.WeightInPounds){
            return d1;
        }else{
            return d2;
        }
    }

    public Dog maxDog(Dog d2){
        if(this.WeightInPounds > d2.WeightInPounds){
            return this;
        }else{
            return d2;
        }
    }

}
