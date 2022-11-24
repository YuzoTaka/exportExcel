/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.feltex.excel.modelo;


// EXPORT
public class DadoCpu {
    private String Id;
    private String UsoCpu;
    private String TempCpu;
    private String DtHrColetaCPU;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUsoCpu() {
        return UsoCpu;
    }

    public void setUsoCpu(String UsoCpu) {
        this.UsoCpu = UsoCpu;
    }

    public String getTempCpu() {
        return TempCpu;
    }

    public void setTempCpu(String TempCpu) {
        this.TempCpu = TempCpu;
    }

    public String getDtHrColetaCPU() {
        return DtHrColetaCPU;
    }

    public void setDtHrColetaCPU(String DtHrColetaCPU) {
        this.DtHrColetaCPU = DtHrColetaCPU;
    }

    @Override
    public String toString() {
        return String.format("ID: %d \nUso: %.2f \n Temperatura: %.2f \nDtHora: %s", Id, UsoCpu, TempCpu, DtHrColetaCPU);
    }
    
    
}
