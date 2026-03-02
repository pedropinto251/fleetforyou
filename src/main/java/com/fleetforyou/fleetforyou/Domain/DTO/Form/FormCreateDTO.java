package com.fleetforyou.fleetforyou.Domain.DTO.Form;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record FormCreateDTO(int satisfaction, String observation, Rental rental) {
    public static FormCreateDTOBuilder builder(){return new FormCreateDTOBuilder();}

    public static class FormCreateDTOBuilder{
        private int satisfaction;
        private String observation;
        private Rental rental;

        FormCreateDTOBuilder() {
        }

        public FormCreateDTOBuilder satisfaction(int satisfaction){
            this.satisfaction = satisfaction;
            return this;
        }

        public FormCreateDTOBuilder observation(String observation){
            this.observation = observation;
            return this;
        }

        public FormCreateDTOBuilder rental(Rental rental){
            this.rental = rental;
            return this;
        }

        public FormCreateDTO build() {
            return new FormCreateDTO(this.satisfaction, this.observation, this.rental);
        }

        public String toString() {
            return "FormCreateDTO.FormCreateDTOBuilder(satisfaction=" + this.satisfaction + ", observation=" + this.observation + ", rental=" + this.rental + ")";
        }
    }
}
