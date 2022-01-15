import {AfterViewInit, Component, ViewChild,Input,OnChanges} from '@angular/core';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatTableDataSource} from "@angular/material/table";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{
    /* for filter*/
    countries: Country[]= [];
    selectedCountry: string= '';
    selectedState: string= '';
    /* customer table */
    ELEMENT_DATA: Customer[] = [];
    isLoading = false;
    totalRows = 0;
    pageSize = 5;
    currentPage = 0;
    country='';
    state='';
    pageSizeOptions: number[] = [5, 10, 15, 20, 30];
    displayedColumns: string[] = ['customerName', 'country', 'countryCode', 'phone', 'state'];
    dataSource: MatTableDataSource<Customer> = new MatTableDataSource();

    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngAfterViewInit() {
     this.dataSource.paginator = this.paginator;
    }
    ngOnInit(): void {
        //Load initial data
        this.loadCountries();
        this.loadCustomerData();
      }

      loadCountries(){
          this.isLoading = true;
          let URL = `http://localhost:8080/country`;

          fetch(URL)
            .then(response => response.json())
            .then(data => {
              this.countries = data.content;
              this.isLoading = false;
            }, error => {
              this.isLoading = false;
            });

          }

     loadCustomerData(){
        this.isLoading = true;
        let URL = `http://localhost:8080/customer?page=${this.currentPage}&size=${this.pageSize}`;
        if(this.country)
          URL +=`&country=${this.country}`;
        if(this.state)
          URL +=`&state=${this.state}`;

        fetch(URL)
          .then(response => response.json())
          .then(data => {
            this.dataSource.data = data.content;
            setTimeout(() => {
              this.paginator.pageIndex = this.currentPage;
              this.paginator.length = data.totalElements;
            });
            this.isLoading = false;
          }, error => {

            this.isLoading = false;
          });

      }
  countryChanged(selectedCountry: string){
    this.pageSize = 5;
    this.currentPage = 0;
    this.country= selectedCountry;
    this.loadCustomerData();
  }
  stateChanged(selectedState: string){
      this.pageSize = 5;
      this.currentPage = 0;
      this.state= selectedState;
      this.loadCustomerData();
    }
  pageChanged(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadCustomerData();
  }
  reset(){
    this.pageSize = 5;
    this.currentPage = 0;
    this.selectedCountry='';
    this.country='';
    this.state='';
    this.selectedState='';
     this.loadCustomerData();
  }
 }

  export interface Customer {
    name: string;
    phone: string;
    country: string;
    countryCode: string;
    state: string;
  }

   export interface Country {
      name: string;
    }
