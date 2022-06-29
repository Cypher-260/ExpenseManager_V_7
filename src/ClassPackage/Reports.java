package ClassPackage;

import java.util.ArrayList;

public class Reports extends Expenses 
{
    private double totalCommitment;
    private double totalExpenses;
    private double totalDebts;
    public double per[] = new double[9];
    public double []result = new double[9];
    public double highest = 0.0;
    public int max = 0;
    public String index[] = new String[9];
    
     public void setTotalCommitment(ArrayList<Fixed_Expenses> commitment,int size) 
    {
        this.totalCommitment = 0.0;
        for(int i = 0;i < size;i++)
                {
                    this.totalCommitment += commitment.get(i).expense;
                }
    }

    public double getTotalCommitment() 
    {
        return this.totalCommitment;
    }
    
    public void setTotalExpenses(ArrayList<Expenses> expenses,int size) 
    {
        this.totalExpenses = 0.0;
        for(int i=0;i<size;i++)
        {
            this.totalExpenses += expenses.get(i).expense;
        }
    }

    public double getTotalExpenses() 
    {
        return this.totalExpenses;
    }
    
    public void setTotalDebts(ArrayList<Debts> debits,int size)
    {
        this.totalDebts = 0.0;
        for(int i=0;i<size;i++)
        {
            this.totalDebts += debits.get(i).expense;
        }
    }
    
    public double getTotalDebts()
    {
        return this.totalDebts;
    }
    
    public void highestCategory(ArrayList<Expenses> expenses,int size)
    {
        
        for(int i = 0;i<9;i++)
            result[i] = 0.0;
        for(int i=0;i<size;i++)
        {
            switch (expenses.get(i).category) 
            {
                case "bussiness":
                    result[0] += expenses.get(i).expense;
                    index[0] = "bussiness";
                    break;
                case "education":
                    result[1] += expenses.get(i).expense;
                    index[1] = "education";
                    break;
                case "food":
                    result[2] += expenses.get(i).expense;
                    index[2] = "food";
                    break;
                case "medical":
                    result[3] += expenses.get(i).expense;
                    index[3] = "medical";
                    break;
                case "fuel":
                    result[4] += expenses.get(i).expense;
                    index[4] = "fuel";
                    break;
                case "entertaiment":
                    result[5] += expenses.get(i).expense;
                    index[5] = "entertaiment";
                    break;
                case "home office":
                    result[6] += expenses.get(i).expense;
                    index[6] = "home office";
                    break;
                case "clothing":
                    result[7] += expenses.get(i).expense;
                    index[7] = "clothing";
                    break;
                default:
                    result[8] += expenses.get(i).expense;
                    index[8] = "others";
                    break;
            }
        }
        for(int i=0;i<9;i++)
        {
             per[i] = (result[i]/this.totalExpenses)*100;
             if(this.highest < result[i])
             {
                 this.highest = result[i];
                 this.max = i;
             }
        }
        System.out.print("\tThe highest category you spent on : "+index[this.max]+
                " and it has "+per[this.max]+" % from the total expenses\n");
    }
    
    public String showPercentages(ArrayList<Expenses> expenses,int size)
    {
        for(int i = 0;i<9;i++)
            result[i] = 0.0;
        for(int i=0;i<size;i++)
        {
            switch (expenses.get(i).category) 
            {
                case "bussiness":
                    result[0] += expenses.get(i).expense;
                    break;
                case "education":
                    result[1] += expenses.get(i).expense;
                    break;
                case "food":
                    result[2] += expenses.get(i).expense;
                    break;
                case "medical":
                    result[3] += expenses.get(i).expense;
                    break;
                case "fuel":
                    result[4] += expenses.get(i).expense;
                    break;
                case "entertaiment":
                    result[5] += expenses.get(i).expense;
                    break;
                case "home office":
                    result[6] += expenses.get(i).expense;
                    break;
                case "clothing":
                    result[7] += expenses.get(i).expense;
                    break;
                default:
                    result[8] += expenses.get(i).expense;
                    break;
            }
        }
        for (int i = 0; i < 9; i++) 
           per[i] = (result[i]/this.totalExpenses)*100;
        String result=
                "\tbussiness = "+per[0]+" %\n"
                +"\teducation = "+per[1]+" %\n"
                +"\tfood = "+per[2]+" %\n"
                +"\tmedical = "+per[3]+" %\n"
                +"\tfuel = "+per[4]+" %\n"
                +"\tentertaiment = "+per[5]+" %\n"
                +"\thome office = "+per[6]+" %\n"
                +"\tclothing = "+per[7]+" %\n"
                +"\tothers = "+per[8]+" %\n";
        return result;
        
    }
    
}
