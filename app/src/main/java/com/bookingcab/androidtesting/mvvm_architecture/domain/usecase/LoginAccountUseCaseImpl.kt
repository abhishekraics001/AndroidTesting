package com.bookingcab.mvvm_architecture.domain.usecase

import com.bookingcab.mvvm_architecture.domain.repository.LoginAccountRepository

class LoginAccountUseCaseImpl(private val loginRepository: LoginAccountRepository): LoginAccountUseCase {

    override suspend fun invoke(): Int = loginRepository.loginAccount()

    override suspend fun executeAPICall(): Int {
        return loginRepository.logOutAccount()
    }
}


/*

====================================================================================================================================
Presentation Layer
    --------------------------------------------------------------------------------------------------------------------------------
    UI
        View => LoginActivity : Your view will inject the viewModel and should be observe the result once call method
                   *****************************************************************************************************************
                  //Declare it on top of class
                  private val vm : LoginAccountViewModel by viewModel()

                  //Inject your KOIN Modules in onCreate method
                  LoginAccountDIModules.inject()

                  //Call your method from any where and observe the data
                   vm.liveData.observe(this) {
                        Log.e("liveData", "liveData: $it")
                    };

                    lifecycleScope.launch {
                        vm.deleteAccount();
                    }



        -------------------------------------------------------------------------------------------------------------------------------
        ViewMode =>  ViewModel (dus : DomainUseCase) => Create one ViewModel Class which accept useCase in constructor as param
                *****************************************************************************************************************
                class LoginAccountViewModel (private val loginAccountUseCase: LoginAccountUseCase ): ViewModel() {
                     var _liveData: MutableLiveData<String> = MutableLiveData("ideal");
                     var liveData: MutableLiveData<String> = _liveData ; //MutableLiveData("ideal");

                        suspend fun deleteAccount(){
                            delay(100)
                            _liveData.value = "init"
                            delay(100)
                            viewModelScope.launch() {

                                _liveData.value =   loginAccountUseCase.invoke().toString()
                                delay(100)
                                _liveData.value = "complete"
                            }
                        }
            }



===================================================================================================================================
Domain Layer
    -------------------------------------------------------------------------------------------------------------------------------
    UseCases
        DomainUseCase (Interface) => Create one Interface in which you define your all use cases
               *****************************************************************************************************************
                //Create one Interface with one abstract method
                interface LoginAccountUseCase {
                    suspend operator fun invoke(): Int
                }


        LoginAccountUseCaseImpl (DomainUseCase Interface Implementation) =>   (dr: DomainRepository) : Implement your domain label useCase and pass your domain label repository in constructor as param
                *****************************************************************************************************************
                class LoginAccountUseCaseImpl(private val repository: LoginAccountRepository): LoginAccountUseCase {
                    override suspend fun invoke(): Int = repository.loginAccount()
                }


   -------------------------------------------------------------------------------------------------------------------------------
   Repository :
         DomainRepository (Interface) Create one more interface / repository on domain label  which one implemented by data layer repository
                *****************************************************************************************************************
                interface LoginAccountRepository {
                    suspend fun loginAccount() : Int
                }




===================================================================================================================================
Data Layer
    -------------------------------------------------------------------------------------------------------------------------------
     DataSources
        DataSources (Interface)
           *****************************************************************************************************************
            interface LoginAccountDataSources {
                suspend fun loginAccount(): Int
            }


     -------------------------------------------------------------------------------------------------------------------------------
     Repository
        DomainRepositoryImpl (DomainRepository  Interface Implementation) => (ds: DataSources) : Create a sub class of your Domain Layer repository interface and pass your data layer dataSources class
        *****************************************************************************************************************
        class LoginAccountRepositoryImpl(private val deleteAccountDataSources: LoginAccountDataSources) :LoginAccountRepository {
            override suspend fun loginAccount(): Int = deleteAccountDataSources.loginAccount()
        }





===================================================================================================================================
Framework
    -------------------------------------------------------------------------------------------------------------------------------
    DataSources
        DataSourcesImpl (DataSources  Interface Implementation) =>  || Create subclass of your data layer LoginAccountDataSources  amd do the your work heare
            *****************************************************************************************************************
            class LoginAccountDataSourcesImpl: LoginAccountDataSources {
                override suspend fun loginAccount(): Int {
                    return xxx();
                }

                fun xxx(): Int{
                    return 1
                }

            }


   -------------------------------------------------------------------------------------------------------------------------------
    DI
        KOIN DI
            object LoginAccountDIModules {

                private val loginAccountModules = module {

                    //useCase
                    factory<LoginAccountUseCase> { LoginAccountUseCaseImpl(get()) }

                    //DataSources
                    single<LoginAccountDataSources> { LoginAccountDataSourcesImpl() }

                    //Repository
                    single<LoginAccountRepository> { LoginAccountRepositoryImpl(get()) }

                    //viewModel
                    viewModel {
                        LoginAccountViewModel(
                            loginAccountUseCase = get()
                        )
                    }
                }


                private val loadKoinModule by lazy{
                    loadKoinModules(loginAccountModules)
                }

                fun inject() = loadKoinModule
            }


 */