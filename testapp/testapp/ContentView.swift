//
//  ContentView.swift
//  testapp
//
//  Created by Oliwier Pachulski on 03/05/2024.
//

import SwiftUI


struct ContentView: View {
    @State public var LoginFieldText: String = ""
    @State public var PasswdFieldText: String = ""
    
    var body: some View{
        //NavigationView
        NavigationView {
            VStack(alignment: .center){
                //VStack for space
                VStack{}
                    .padding(.top, 300.0)
                TextField("Login", text: $LoginFieldText)
                    .padding()
                    .frame(maxWidth: 300)
                    .background(Color.gray
                        .opacity(0.3)
                        .cornerRadius(10))
                    .font(.headline)
                TextField("Password", text: $PasswdFieldText)
                    .padding()
                    .frame(maxWidth: 300)
                    .background(Color.gray
                        .opacity(0.3)
                        .cornerRadius(10))
                    .font(.headline)
                
                
                //HStack
                HStack(alignment: .bottom){
                    Button(action: {
                    }, label: {
                        Text("Login")
                            .multilineTextAlignment(.leading)
                            .padding()
                            .frame(maxWidth: 100)
                            .foregroundColor(.white)
                            .background(
                                Color.blue
                                    .cornerRadius(10))
                            .font(.headline)
                    })
                    
                    Button(action: {
                    }, label: {
                        Text("Sign up")
                            .multilineTextAlignment(.leading)
                            .padding()
                            .frame(maxWidth: 100)
                            .foregroundColor(.white)
                            .background(
                                Color.blue
                                    .cornerRadius(10))
                            .font(.headline)
                    })
                    
                }.padding(.top)
            }
            .padding(0.0)
            .navigationTitle("Welcome Back!")
        }//.background(Color.black)
    }
}

struct ContentView_Preview: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
