import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MytestComponent } from './mytest/mytest.component';
import { MydiagnosticcentreComponent } from './mydiagnosticcentre/mydiagnosticcentre.component';
import { CreateTestComponent } from './create-test/create-test.component';
import { CreateDiagnosticcentreComponent } from './create-diagnosticcentre/create-diagnosticcentre.component';
import { MyappointmentComponent } from './myappointment/myappointment.component';
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import { UpdateTestComponent } from './update-test/update-test.component';
import { UpdateDiagnosticcentreComponent } from './update-diagnosticcentre/update-diagnosticcentre.component';
import { UpdateAppointmentComponent } from './update-appointment/update-appointment.component';


const routes: Routes = [
  // {path:'',
  // redirectTo:'test',
  // pathMatch:'full'
  // },
  {path:'mytest',
    component: MytestComponent
  },
  {path: 'addtest',
   component: CreateTestComponent
  },
  {path:'mydiagnosticCentre',
    component: MydiagnosticcentreComponent
  },
  {path: 'adddiagnosticcentre',
   component: CreateDiagnosticcentreComponent
  },
  {path:'myappointment',
    component: MyappointmentComponent
  },
  {path: 'addappointment',
   component: CreateAppointmentComponent
  },
  {path: 'updateTest/:id',
   component: UpdateTestComponent
  },
  {path: 'updateDiagnosticCentre/:id',
   component: UpdateDiagnosticcentreComponent
  },
  {path: 'updateAppointment/:id',
   component: UpdateAppointmentComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
