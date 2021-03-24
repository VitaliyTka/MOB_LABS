// <auto-generated/>
#pragma warning disable 1591
#pragma warning disable 0414
#pragma warning disable 0649
#pragma warning disable 0169

namespace MOB_SR.Pages
{
    #line hidden
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Components.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.AspNetCore.Components.Web.Virtualization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using MOB_SR;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using MOB_SR.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Radzen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 12 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\_Imports.razor"
using Radzen.Blazor;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\Pages\Index.razor"
using System;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\Pages\Index.razor"
using System.IO;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/")]
    public partial class Index : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
        }
        #pragma warning restore 1998
#nullable restore
#line 127 "C:\Users\VITALIY\source\repos\MOB_SR\MOB_SR\Pages\Index.razor"
      
    private string A = "";
    private string B = "";
    private string EX = "";
    private string RESULT = "RESULT";
    private string ERROR_A = "";
    private string ERROR_B = "";
    private string ERROR_EX = "";

    private string ROW_EX = "";
    private string ROW_1 = "";
    private string ROW_2 = "";
    private string ROW_3 = "";
    private string ROW_4 = "";
    private string ROW_5 = "";
    private string ROW_6 = "";
    private string ROW_7 = "";
    private string ROW_8 = "";
    private string ROW_9 = "";
    private string ROW_A = "";
    private string ROW_B = "";
    private string ROW_EXX = "";

    public int StringToInt(String a)
    {
        return int.Parse(a);
    }
    public Boolean IsCurrentInt(String a)
    {
        if (a == "0" || a == "1" || a == "-1")
        {
            return true;
        }
        return false;
    }
    public List<String> StringToList(String ex)
    {
        List<String> rez = new List<string>();
        for (int i = 0; i < ex.Length; i++)
        {
            rez.Add(ex.ElementAt(i).ToString());
        }
        return rez;
    }
    public void Load()
    {
        string textFromFile = "";
        string path = @".\wwwroot\hta.txt";
        using (FileStream fstream = File.OpenRead($"{path}"))
        {
            // преобразуем строку в байты
            byte[] array = new byte[fstream.Length];
            // считываем данные
            fstream.Read(array, 0, array.Length);
            // декодируем байты в строку
            textFromFile = System.Text.Encoding.Default.GetString(array);
        }
        string[] mystring = textFromFile.Split('\n');
        ROW_A = mystring[0];
        ROW_B = mystring[1];
        ROW_EXX = mystring[2];
    }
    public void Save()
    {
        string writePath = @".\wwwroot\hta.txt";

        string text = "A : " + A + "\n" +
            "B : " + B + "\n" +
            "Boolean Expression : " + EX;
        try
        {
            using (StreamWriter sw = new StreamWriter(writePath, false, System.Text.Encoding.Default))
            {
                sw.WriteLine(text);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e.Message);
        }
    }
    public void Rez()
    {
        ERROR_A = "";
        ERROR_B = "";
        ERROR_EX = "";
        Boolean isTrue = true;
        ROW_EX = "A/B/";
        ROW_1 = "-1/-1/";
        ROW_2 = "-1/0/";
        ROW_3 = "-1/1/";
        ROW_4 = "0/-1/";
        ROW_5 = "0/0/";
        ROW_6 = "0/1/";
        ROW_7 = "1/-1/";
        ROW_8 = "1/0/";
        ROW_9 = "1/1/";

        if (A == "" || !IsCurrentInt(A))
        {
            isTrue = false;
            ERROR_A = "|A is not current|";
        }
        if (B == "" || !IsCurrentInt(B))
        {
            isTrue = false;
            ERROR_B = "|B is not current|";
        }
        if (EX == "")
        {
            isTrue = false;
            ERROR_EX = "|Expression is not current|";
        }
        EX = EX.Replace(" ", "");
        EX.ToLower();
        List<String> EX_LIST = new List<string>();
        EX_LIST = StringToList(EX);
        for (int i = 0; i < EX_LIST.Count; i++)
        {
            if (i + 1 != EX_LIST.Count && EX_LIST.ElementAt(i) == "a" && EX_LIST.ElementAt(i + 1) == "a")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            if (i + 1 != EX_LIST.Count && EX_LIST.ElementAt(i) == "a" && EX_LIST.ElementAt(i + 1) == "b")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            if (i + 1 != EX_LIST.Count && EX_LIST.ElementAt(i) == "b" && EX_LIST.ElementAt(i + 1) == "a")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            if (i + 1 != EX_LIST.Count && EX_LIST.ElementAt(i) == "b" && EX_LIST.ElementAt(i + 1) == "b")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            if (i + 1 != EX_LIST.Count && EX_LIST.ElementAt(i) == "!" && EX_LIST.ElementAt(i + 1) == "!")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            if (i + 2 != EX_LIST.Count && EX_LIST.ElementAt(i) == "!" && EX_LIST.ElementAt(i + 2) == "!")
            {
                ERROR_EX = "|Expression is not current|";
                isTrue = false;
            }
            switch (EX_LIST.ElementAt(i))
            {
                case "a": break;
                case "b": break;
                case "!": break;
                case "&": break;
                case "v": break;
                case "+": break;
                case "*": break;
                case "|": break;
                case "?": break;
                default:
                    ERROR_EX = "|Expression is not current|";
                    isTrue = false;
                    break;
            }
        }

        //todo
        try
        {
            //todo
            List<String> LEX = copyList(EX_LIST);
            List<String> L1 = copyList(EX_LIST);
            List<String> L2 = copyList(EX_LIST);
            List<String> L3 = copyList(EX_LIST);
            List<String> L4 = copyList(EX_LIST);
            List<String> L5 = copyList(EX_LIST);
            List<String> L6 = copyList(EX_LIST);
            List<String> L7 = copyList(EX_LIST);
            List<String> L8 = copyList(EX_LIST);
            List<String> L9 = copyList(EX_LIST);
            ROW_1 += GetTableRow(L1, "-1", "-1");
            ROW_2 += GetTableRow(L2, "-1", "0");
            ROW_3 += GetTableRow(L3, "-1", "1");
            ROW_4 += GetTableRow(L4, "0", "-1");
            ROW_5 += GetTableRow(L5, "0", "0");
            ROW_6 += GetTableRow(L6, "0", "1");
            ROW_7 += GetTableRow(L7, "1", "-1");
            ROW_8 += GetTableRow(L8, "1", "0");
            ROW_9 += GetTableRow(L9, "1", "1");
            ROW_EX += GetTableEX(LEX, A, B);
            RESULT = GetRez(EX_LIST, A, B);
        }
        catch (Exception ex)
        {
            isTrue = false;
            ERROR_EX = "|Expression is not current|";
            Console.WriteLine(ex);
        }

        if (!isTrue)
        {
            ROW_EX = "";
            ROW_1 = "";
            ROW_2 = "";
            ROW_3 = "";
            ROW_4 = "";
            ROW_5 = "";
            ROW_6 = "";
            ROW_7 = "";
            ROW_8 = "";
            ROW_9 = "";
            return;
        }

    }
    List<String> copyList(List<String> ExListt)
    {
        List<String> newList = new List<string>();
        for(int i = 0; i < ExListt.Count; i++)
        {
            newList.Add(ExListt.ElementAt(i));
        }
        return newList;
    }
    void printList(List<String> ExListt)
    {
        Console.WriteLine("====================");
        for (int i = 0; i < ExListt.Count; i++)
        {
            Console.WriteLine(ExListt.ElementAt(i));
        }
        Console.WriteLine("====================");
    }
    String GetRez(List<String> ExListt, String aZnach, String bZnach)
    {

        List<String> ExListtt = ExListt;
        String inp_a = aZnach;
        String inp_b = bZnach;
        int a = StringToInt(inp_a);
        int b = StringToInt(inp_b);
        for (int i = 0; i < ExListtt.Count; i++)
        {
            if (ExListtt.ElementAt(i) == "a")
            {
                ExListtt.Insert(i, inp_a);
                ExListtt.RemoveAt(i + 1);
            }
            if (ExListtt.ElementAt(i) == "b")
            {
                ExListtt.Insert(i, inp_b);
                ExListtt.RemoveAt(i + 1);
            }
        }
        int size = ExListtt.Count;
        for (int i = 0; i < size; i++)
        {
            if (ExListtt.ElementAt(i) == "!")
            {
                //                System.out.println("============= rez : " + rez);
                ExListtt.Insert(i, Denial_t(ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i + 1);
                ExListtt.RemoveAt(i + 1);
                size = size - 1;
            }
            if (ExListtt.ElementAt(i) == "|")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, SchaeffersStroke(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "?")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, PiercesArrow(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "&")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, WeakConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "v")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, WeakDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "+")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, StrongDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "*")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                ExListtt.Insert(i, StrongConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
        }
        printList(ExListtt);
        return ExListtt.ElementAt(0);
    }
    String GetTableEX(List<String> ExListTable, String aZnach, String bZnach)
    {
        String TableRowExt = "";
        int ExListTableSize = ExListTable.Count;
        for (int i = 0; i < ExListTableSize; i++)
        {
            if (ExListTable.ElementAt(i) == "!")
            {
                String buff = ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + "(" + buff + ")";
                ExListTable.Insert(i, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i + 1);
                ExListTableSize = ExListTableSize - 1;
            }
            if (ExListTable.ElementAt(i) == "v")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if (ExListTable.ElementAt(i) == "?")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if (ExListTable.ElementAt(i) == "|")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if (ExListTable.ElementAt(i) == "&")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if (ExListTable.ElementAt(i) == "+")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if (ExListTable.ElementAt(i) == "*")
            {
                if (ExListTable.ElementAt(i + 1) == "!")
                {
                    String bufff = ExListTable.ElementAt(i + 1) + ExListTable.ElementAt(i + 2);
                    bufff = bufff.Replace("/", "");
                    String Exx = "(" + bufff + ")";
                    TableRowExt += "/" + Exx;
                    ExListTable.Insert(i + 1, Exx);
                    ExListTable.RemoveAt(i + 2);
                    ExListTable.RemoveAt(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.ElementAt(i - 1) + ExListTable.ElementAt(i) + ExListTable.ElementAt(i + 1);
                buff = buff.Replace("/", "");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.Insert(i - 1, Ex);
                ExListTable.RemoveAt(i + 1);
                ExListTable.RemoveAt(i);
                ExListTable.RemoveAt(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
        }

        return TableRowExt;
    }
    String GetTableRow(List<String> ExListt, String aZnach, String bZnach)
    {

        List<String> ExListtt = ExListt;
        String rez = "";
        String inp_a = aZnach;
        String inp_b = bZnach;
        int a = StringToInt(inp_a);
        int b = StringToInt(inp_b);
        for (int i = 0; i < ExListtt.Count; i++)
        {
            if (ExListtt.ElementAt(i) == "a")
            {
                ExListtt.Insert(i, inp_a);
                ExListtt.RemoveAt(i + 1);
            }
            if (ExListtt.ElementAt(i) == "b")
            {
                ExListtt.Insert(i, inp_b);
                ExListtt.RemoveAt(i + 1);
            }
        }
        int size = ExListtt.Count;
        for (int i = 0; i < size; i++)
        {
            if (ExListtt.ElementAt(i) == "!")
            {
                rez += Denial_t(ExListtt.ElementAt(i + 1)).ToString() + "/";
                //                System.out.println("============= rez : " + rez);
                ExListtt.Insert(i, Denial_t(ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i + 1);
                ExListtt.RemoveAt(i + 1);
                size = size - 1;
            }
            if (ExListtt.ElementAt(i) == "|")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += SchaeffersStroke(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, SchaeffersStroke(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "?")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += PiercesArrow(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, PiercesArrow(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "&")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += WeakConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, WeakConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "v")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += WeakDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, WeakDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "+")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += StrongDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, StrongDisjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
            if (ExListtt.ElementAt(i) == "*")
            {
                if (ExListtt.ElementAt(i + 1) == "!")
                {
                    rez += Denial_t(ExListtt.ElementAt(i + 2)).ToString() + "/";
                    ExListtt.Insert(i + 1, Denial_t(ExListtt.ElementAt(i + 2)).ToString());
                    ExListtt.RemoveAt(i + 2);
                    ExListtt.RemoveAt(i + 2);
                    size = size - 1;
                }
                rez += StrongConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString() + "/";
                ExListtt.Insert(i, StrongConjunction(ExListtt.ElementAt(i - 1), ExListtt.ElementAt(i + 1)).ToString());
                ExListtt.RemoveAt(i - 1);
                ExListtt.RemoveAt(i);
                ExListtt.RemoveAt(i);
                i = i - 1;
                size = size - 2;
            }
        }
        return rez;
    }
    public int setIntParam(String a)
    {
        if (a == "1")
        {
            return 1;
        }
        if (a == "-1")
        {
            return -1;
        }
        return 0;
    }
    public int Denial_t(String a)
    {
        int a_param = setIntParam(a);
        if (a_param == 1) return -1;
        if (a_param == -1) return 1;
        return 0;
    }
    public int WeakConjunction(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == 1) return 1;
        if (a_param == -1 || b_param == -1) return -1;
        return 0;
    }
    public int WeakDisjunction(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == -1) return -1;
        if (a_param == 1 || b_param == 1) return 1;
        return 0;
    }
    public int StrongConjunction(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == 1) return 1;
        if (a_param == b_param && a_param == 0) return -1;
        if (a_param == -1 || b_param == -1) return -1;
        return 0;
    }
    public int StrongDisjunction(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == -1) return -1;
        if (a_param == b_param && a_param == 0) return 1;
        if (a_param == 1 || b_param == 1) return 1;
        return 0;
    }
    public int PiercesArrow(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == -1) return 1;
        if (a_param == 1 || b_param == 1) return -1;
        return 0;
    }
    public int SchaeffersStroke(String a, String b)
    {
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if (a_param == b_param && a_param == 1) return -1;
        if (a_param == -1 || b_param == -1) return 1;
        return 0;
    }

#line default
#line hidden
#nullable disable
    }
}
#pragma warning restore 1591
