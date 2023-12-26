import { Component, OnInit } from '@angular/core';
import { InsurancesService } from 'src/app/services/insurances.service';


@Component({
  selector: 'app-insurance-details-page',
  templateUrl: './insurance-details-page.component.html',
  styleUrl: './insurance-details-page.component.scss'
})
export class InsuranceDetailsPageComponent implements OnInit{
  insurances:any=[];
  displayedColumns: string[] = ['id', 'userid', 'type','startDate'];
  dataSource = this.insurances;
  constructor(private insuranceService:InsurancesService){
    console.log("heloo from insurace")
  }
  ngOnInit(){
    this.insurances=this.insuranceService.insurances;
    this.dataSource=this.insurances;
  }

}
